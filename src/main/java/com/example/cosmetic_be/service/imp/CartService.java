package com.example.cosmetic_be.service.imp;

import com.example.cosmetic_be.model.*;
import com.example.cosmetic_be.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service

public class CartService {
    @Autowired
    private ICartRepository iCartRepository;
    @Autowired
    private IAccountsRepository iAccountsRepository;
    @Autowired
    private ICartItemRepository iCartItemRepository; // Thêm repository cho CartItem

    @Autowired
    private IProductRepository iProductRepository; // Thêm repository cho Product
    @Autowired
    private IProductVariantsRepository iProductVariantsRepository; // Thêm repository cho ProductVariants

    public Cart createCart(Long accountId) {
//        tìm giỏ hàng bằng id người dùng
        Cart cart = iCartRepository.findByAccountsId(accountId);
        if (cart == null) {
//            tìm tài khoản dựa vào id
            Optional<Accounts> accounts = iAccountsRepository.findById(accountId);
            Accounts accounts1 = accounts.orElseThrow(() -> new RuntimeException("Không tìm thấy tài khoản"));
            cart = new Cart();
            cart.setAccounts(accounts1);
            cart.setCreatedAt(LocalDateTime.now());
            cart.setUpdatedAt(LocalDateTime.now());
            iCartRepository.save(cart);


        }
        return cart;
    }

    public void addProductToCart(Long accountId, Long productId, Long variantId, int quantity) {
        // Tìm hoặc tạo giỏ hàng cho người dùng
        Cart cart = createCart(accountId);

        // Tìm sản phẩm
        Products product = iProductRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Sản phẩm không tồn tại"));

        // Tìm biến thể nếu variantId không null
        ProductVariants variant = null;
        if (variantId != null) {
            variant = iProductVariantsRepository.findById(variantId)
                    .orElseThrow(() -> new RuntimeException("Biến thể sản phẩm không tồn tại"));
        }

        // Tìm mục giỏ hàng (CartItem) hiện tại với sản phẩm và biến thể
        CartItems cartItem = iCartItemRepository.findByCartAndProductAndVariant(cart, product, variant);
        if (cartItem != null) {
            // Cập nhật số lượng nếu đã có sản phẩm trong giỏ hàng
            cartItem.setQuantity(cartItem.getQuantity() + quantity);
        } else {
            // Tạo mục giỏ hàng mới nếu chưa có
            cartItem = new CartItems();
            cartItem.setCart(cart);
            cartItem.setProduct(product);
            cartItem.setVariant(variant); // Có thể là null nếu không có biến thể
            cartItem.setQuantity(quantity);
        }

        // Lưu mục giỏ hàng vào cơ sở dữ liệu
        iCartItemRepository.save(cartItem);
    }
//    Nếu có bất kỳ lỗi nào xảy ra trong quá trình thực hiện các thao tác này, toàn bộ các thao tác phải được hoàn tác (rollback) để đảm bảo tính nhất quán của dữ liệu.
    @Transactional
    public void updateQuantity(Long itemId, int newQuantity) {
        CartItems cartItem = iCartItemRepository.findById(itemId)
                .orElseThrow(() -> new RuntimeException("Mục giỏ hàng không tồn tại"));

        if (newQuantity <= 0) {
            iCartItemRepository.delete(cartItem);
        } else {
            // Cập nhật số lượng
            cartItem.setQuantity(newQuantity);
            iCartItemRepository.save(cartItem);
        }
    }

}
